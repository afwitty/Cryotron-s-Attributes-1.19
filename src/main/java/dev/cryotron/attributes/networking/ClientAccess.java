package dev.cryotron.attributes.networking;

import dev.cryotron.attributes.client.events.TotemOfInvulnerabilityClientEvent;

public class ClientAccess {
    public static boolean reachPlayerClientInvulnerability(boolean invulnerabilityUsed) {

    	TotemOfInvulnerabilityClientEvent.totemActivated = invulnerabilityUsed;
    	
    	return true;
    }
}
