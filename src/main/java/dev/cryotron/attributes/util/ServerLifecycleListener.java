package dev.cryotron.attributes.util;

import javax.annotation.Nullable;

public interface ServerLifecycleListener {
	  void onServerStart();
	  
	  void onServerStop();
	  
	  static ServerLifecycleListener stop(Runnable onStop) {
	    return wrap(null, onStop);
	  }
	  
	  static ServerLifecycleListener start(Runnable onStart) {
	    return wrap(onStart, null);
	  }
	  
	  static ServerLifecycleListener wrap(@Nullable final Runnable onStart, @Nullable final Runnable onStop) {
	    return new ServerLifecycleListener() {
	        public void onServerStart() {
	          if (onStart != null)
	            onStart.run(); 
	        }
	        
	        public void onServerStop() {
	          if (onStop != null)
	            onStop.run(); 
	        }
	      };
	  }
}
