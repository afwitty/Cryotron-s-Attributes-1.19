package dev.cryotron.attributes.common.skilltree;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PreparedSkillTreeData {
	
      private final List<SkillTreePoint<AbstractSkill>> treePoints = new LinkedList<>();
	
	  private final Map<AbstractSkill, Collection<AbstractSkill>> doubleConnections = new HashMap<>();
	
	  private final List<Tuple<AbstractSkill, AbstractSkill>> connections = new LinkedList<>();
	  
	    private long version = 0;
	  
	    static PreparedSkillTreeData create(Collection<LoadedSkillData> skills) {
	        PreparedSkillTreeData treeData = new PreparedSkillTreeData();

	        skills.stream().map(LoadedSkillData::getSkill).forEach(perk -> {
//	            if (perk instanceof RootPerk) {
//	                treeData.rootPerks.put(((RootPerk) perk).getConstellation(), (RootPerk) perk);
//	            }
	            SkillTreePoint<? extends AbstractSkill> offsetPoint = perk.getPoint();
	            if (treeData.treePoints.contains(offsetPoint)) {
	                throw new IllegalArgumentException("Tried to register skill-point at already placed position: " + offsetPoint.getOffset().toString());
	            }
	            treeData.treePoints.add((SkillTreePoint<AbstractSkill>) offsetPoint);
	        });
	        skills.forEach(skillData -> {
	            for (ResourceLocation connection : skillData.getConnections()) {
	                AbstractSkill skillTo = treeData.getSkill(skill -> connection.equals(skill.getRegistryName()))
	                        .orElseThrow(() -> new JsonParseException("Cannot connect to unknown skill: " + connection));
	                treeData.getConnector(skillTo).ifPresent(connector -> {
	                    connector.connect(skillData.getSkill());
	                });
	            }
	        });

	        treeData.version = treeData.computeTreeHash();
	        return treeData;
	    }

	    public long getVersion() {
	        return version;
	    }
	    
	    public Optional<AbstractSkill> getSkill(Predicate<AbstractSkill> test) {
	        return this.treePoints.stream().map(SkillTreePoint::getSkill).filter(test).findFirst();
	    }
	
	    public Optional<? extends AbstractSkill> getSkill(float x, float y) {
	        return this.treePoints.stream()
	                .filter(treePoint -> treePoint.getOffset().distance(x, y) <= 1E-4)
	                .findFirst()
	                .map(SkillTreePoint::getSkill);
	    }
	    
	    private Optional<PointConnector> getConnector(AbstractSkill point) {
	        if (point == null) {
	            return Optional.empty();
	        }
	        if (this.treePoints.contains(point.getPoint())) {
	            return Optional.of(new PointConnector(point));
	        }
	        return Optional.empty();
	    }
	  
	  public Collection<AbstractSkill> getConnectedSkills(AbstractSkill skill) {
		    return this.doubleConnections.getOrDefault(skill, Lists.newArrayList());
		  }
	
	  @OnlyIn(Dist.CLIENT)
	  public Collection<Tuple<AbstractSkill, AbstractSkill>> getConnections() {
	    return Collections.unmodifiableList(this.connections);
	  }
	  
	  private long computeTreeHash() {
		    long[] perkHash = new long[this.treePoints.size()];
		    for (int i = 0; i < this.treePoints.size(); i++) {
		      SkillTreePoint<? extends AbstractSkill> treePoint = this.treePoints.get(i);
		      perkHash[i] = treePoint.getSkill().hashCode() << 32L ^ treePoint.getOffset().hashCode();
		    } 
		    long hash = 1L;
		    for (long element : perkHash) {
		      long elementHash = element ^ element >>> 32L;
		      hash = 31L * hash + elementHash;
		    } 
		    return hash;
		  }
	  
	    public class PointConnector {

	        private final AbstractSkill point;

	        private PointConnector(AbstractSkill point) {
	            this.point = point;
	        }

	        public PointConnector connect(AbstractSkill other) {
	            if (other == null) {
	                return this;
	            }

	            Collection<AbstractSkill> pointsTo = doubleConnections.computeIfAbsent(other, p -> new LinkedList<>());
	            if (!pointsTo.contains(point)) {
	                pointsTo.add(point);
	            }
	            pointsTo = doubleConnections.computeIfAbsent(point, p -> new LinkedList<>());
	            if (!pointsTo.contains(other)) {
	                pointsTo.add(other);
	            }

	            Tuple<AbstractSkill, AbstractSkill> connection = new Tuple<>(point, other);
	            Tuple<AbstractSkill, AbstractSkill> reverse = new Tuple<>(other, point);
	            if (!connections.contains(connection) && !connections.contains(reverse)) {
	                connections.add(connection);
	            }
	            return this;
	        }

	        public PointConnector connect(PointConnector other) {
	            return connect(other.point);
	        }

	        public PointConnector chain(PointConnector other) {
	            connect(other.point);
	            return other;
	        }
	    }
}
