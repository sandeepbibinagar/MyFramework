package com.ics.seleniumCoreUtilis;

import java.lang.reflect.Field;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			*/
/* Purpose:	 Code for scenario-template creation                                                                	*/
/********************************************************************************************************************/
public class ScenarioTextGenerator {
	public <Input extends Component, Output extends Component> String generate(Scenario<Input, Output> scenario) {
		StringBuilder values = new StringBuilder();
		Field[] fields = scenario.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (!values.toString().isEmpty()) {

				values.append(", ");
			}
			try {
				field.setAccessible(true);
				values.append(field.getName() + ": " + field.get(scenario));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				values.append(field.getName() + "!");
			}
		}
		return scenario.getClass().getSimpleName() + "(" + values.toString() + ")";
	}
}