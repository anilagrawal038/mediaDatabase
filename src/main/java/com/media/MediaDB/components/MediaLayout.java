package com.media.MediaDB.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class MediaLayout {
	@Property
	  @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	  private String title;
}
