import com.google.inject.AbstractModule;

import core.Global;

public class Module extends AbstractModule {

	@Override
	public void configure() {
		bind(Global.class).asEagerSingleton();
	}

}
