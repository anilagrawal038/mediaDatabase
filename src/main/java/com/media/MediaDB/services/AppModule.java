package com.media.MediaDB.services;

import java.io.IOException;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.*;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StackExtension;
import org.apache.tapestry5.services.javascript.StackExtensionType;
import org.slf4j.Logger;

import com.media.MediaDB.dao.GenericDAO;
import com.media.MediaDB.worker.CommonMediaService;
import com.media.MediaDB.worker.MediaFileCreater;
import com.media.MediaDB.worker.MediaFileCreaterImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */
public class AppModule {
	public static void bind(ServiceBinder binder) {
		// binder.bind(MyServiceInterface.class, MyServiceImpl.class);

		// Make bind() calls on the binder object to define most IoC services.
		// Use service builder methods (example below) when the implementation
		// is provided inline, or requires more initialization than simply
		// invoking the constructor.
		binder.bind(MediaFileCreater.class);
		binder.bind(GenericDAO.class);
		binder.bind(CommonMediaService.class);
	}

	public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
		// The values defined here (as factory default overrides) are themselves
		// overridden with application defaults by DevelopmentModule and
		// QaModule.

		// The application version is primarily useful as it appears in
		// any exception reports (HTML or textual).
		configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");

		// This is something that should be removed when going to production,
		// but is useful
		// in the early stages of development.
		configuration.override(SymbolConstants.PRODUCTION_MODE, false);
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
		// Contributions to ApplicationDefaults will override any contributions
		// to
		// FactoryDefaults (with the same key). Here we're restricting the
		// supported
		// locales to just "en" (English). As you add localised message catalogs
		// and other assets,
		// you can extend this list of locales (it's a comma separated series of
		// locale names;
		// the first locale name is the default when there's no reasonable
		// match).
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

		// You should change the passphrase immediately; the HMAC passphrase is
		// used to secure
		// the hidden field data stored in forms to encrypt and digitally sign
		// client-side data.
		configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately");
	}

	/**
	 * Use annotation or method naming convention:
	 * <code>contributeApplicationDefaults</code>
	 */
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void setupEnvironment(MappedConfiguration<String, Object> configuration) {
		// Support for jQuery is new in Tapestry 5.4 and will become the only
		// supported
		// option in 5.5.
		configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
		configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:mybootstrap");
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, true);
	}

	public RequestFilter buildTimingFilter(final Logger log) {
		return new RequestFilter() {
			public boolean service(Request request, Response response, RequestHandler handler) throws IOException {
				long startTime = System.currentTimeMillis();

				try {
					// The responsibility of a filter is to invoke the
					// corresponding method
					// in the handler. When you chain multiple filters together,
					// each filter
					// received a handler that is a bridge to the next filter.

					return handler.service(request, response);
				} finally {
					long elapsed = System.currentTimeMillis() - startTime;

					log.info("Request time: {} ms", elapsed);
				}
			}
		};
	}

	@Contribute(RequestHandler.class)
	public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration, @Local RequestFilter filter) {
		// Each contribution to an ordered configuration has a name, When
		// necessary, you may
		// set constraints to precisely control the invocation order of the
		// contributed filter
		// within the pipeline.

		configuration.add("Timing", filter);
	}

	// For auto scanning rest services by the name of package
	public static void contributeResteasyPackageManager(Configuration<String> configuration) {
		configuration.add("com.media.MediaDB.rest");
	}

	// For manual registration of rest services
	/*
	 * @Contribute(javax.ws.rs.core.Application.class) public static void
	 * configureRestResources(Configuration<Object> singletons,
	 * MyDomainObjectResource myDomainObjectResource) {
	 * singletons.add(myDomainObjectResource); }
	 */

}
