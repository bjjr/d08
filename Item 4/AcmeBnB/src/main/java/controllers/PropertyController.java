
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeValueService;
import services.PropertyService;
import domain.AttributeValue;
import domain.Property;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService			propertyService;

	@Autowired
	private AttributeValueService	attributeValueService;


	public PropertyController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int propertyId) {
		ModelAndView result;
		Property property;
		Collection<AttributeValue> attributeValues;

		property = propertyService.findOne(propertyId);
		attributeValues = attributeValueService.findAttributesValuesByProperty(propertyId);
		result = new ModelAndView("property/display");
		result.addObject("requestURI", "property/display.do");
		result.addObject("property", property);
		result.addObject("attributes", attributeValues);

		return result;

	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Property> properties;

		properties = propertyService.findAll();
		result = new ModelAndView("property/list");
		result.addObject("requestURI", "property/list.do");
		result.addObject("properties", properties);

		return result;

	}

	protected ModelAndView createEditModelAndView(Property property) {
		ModelAndView result;

		result = createEditModelAndView(property, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Property property, String message) {
		ModelAndView result;

		result = new ModelAndView("property/edit");
		result.addObject("property", property);
		result.addObject("message", message);

		return result;
	}

}
