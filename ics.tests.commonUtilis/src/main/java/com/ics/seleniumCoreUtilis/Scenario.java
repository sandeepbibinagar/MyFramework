package com.ics.seleniumCoreUtilis;

import java.io.IOException;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			*/
/* Purpose:	 Scenario Interface to implement for Web-pages                                                          */
/********************************************************************************************************************/
public interface Scenario<Input extends Component, Output extends Component> {
	Output run(Input entry) throws IOException, InterruptedException, Exception;
}
