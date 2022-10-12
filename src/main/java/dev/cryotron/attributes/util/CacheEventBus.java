package dev.cryotron.attributes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.IEventBusInvokeDispatcher;

public class CacheEventBus implements IEventBus {
	  private final List<Object> registeredListeners = new ArrayList();
	  
	  private final IEventBus wrapped;
	  
	  private CacheEventBus(IEventBus wrapped) {
	    this.wrapped = wrapped;
	  }
	  
	  public static CacheEventBus of(IEventBus bus) {
	    return new CacheEventBus(bus);
	  }
	  
	  public void unregisterAll() {
	    this.registeredListeners.forEach(this.wrapped::unregister);
	    this.registeredListeners.clear();
	  }
	  
	  public void register(Object target) {
	    this.wrapped.register(target);
	    this.registeredListeners.add(target);
	  }
	  
	  public <T extends Event> void addListener(Consumer<T> consumer) {
	    this.wrapped.addListener(consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends Event> void addListener(EventPriority priority, Consumer<T> consumer) {
	    this.wrapped.addListener(priority, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
	    this.wrapped.addListener(priority, receiveCancelled, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
	    this.wrapped.addListener(priority, receiveCancelled, eventType, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends net.minecraftforge.eventbus.api.GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, Consumer<T> consumer) {
	    this.wrapped.addGenericListener(genericClassFilter, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends net.minecraftforge.eventbus.api.GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, Consumer<T> consumer) {
	    this.wrapped.addGenericListener(genericClassFilter, priority, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends net.minecraftforge.eventbus.api.GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
	    this.wrapped.addGenericListener(genericClassFilter, priority, receiveCancelled, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public <T extends net.minecraftforge.eventbus.api.GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
	    this.wrapped.addGenericListener(genericClassFilter, priority, receiveCancelled, eventType, consumer);
	    this.registeredListeners.add(consumer);
	  }
	  
	  public void unregister(Object object) {
	    this.wrapped.unregister(object);
	    this.registeredListeners.remove(object);
	  }
	  
	  public boolean post(Event event) {
	    return this.wrapped.post(event);
	  }
	  
	  public boolean post(Event event, IEventBusInvokeDispatcher wrapper) {
	    return this.wrapped.post(event, wrapper);
	  }
	  
	  public void shutdown() {
	    this.wrapped.shutdown();
	  }
	  
	  public void start() {
	    this.wrapped.start();
	  }
}
