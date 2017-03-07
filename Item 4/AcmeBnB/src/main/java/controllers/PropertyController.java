
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.AttributeValueService;
import services.AuditorService;
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

	@Autowired
	private AuditorService			auditorService;


	public PropertyController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Property property;

		property = propertyService.create();
		result = createEditModelAndView(property);

		return result;
	}

	//Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId) {
		ModelAndView result;
		Property property;

		property = propertyService.findOneToEdit(propertyId);
		Assert.notNull(property);
		result = createEditModelAndView(property);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Property property, BindingResult binding) {
		ModelAndView result;
		Property save;

		save = propertyService.reconstruct(property, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(property);
		} else {
			try {
				propertyService.save(save);
				result = new ModelAndView("redirect:/property/ownList.do");
				result.addObject("message", "property.commit.ok");
			} catch (Throwable oops) {
				result = createEditModelAndView(property, "property.commit.error");
			}
		}

		return result;
	}

	// Deleting ------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Property property, BindingResult binding) {
		ModelAndView res;

		property = propertyService.reconstruct(property, binding);

		try {
			propertyService.delete(property);
			res = new ModelAndView("redirect:ownList.do");
		} catch (Throwable th) {
			if (th.getMessage().equals("You have pending books")) {
				res = createEditModelAndView(property, "property.commit.error.books");
			} else if (th.getMessage().equals("Your property was audited")) {
				res = createEditModelAndView(property, "property.commit.error.audits");
			} else {
				res = createEditModelAndView(property, "property.commit.error");
			}
		}

		return res;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int propertyId) {
		ModelAndView result;
		Property property;
		Collection<AttributeValue> attributeValues;
		Boolean isOwner = false;

		property = propertyService.findOne(propertyId);
		attributeValues = attributeValueService.findAttributesValuesByProperty(propertyId);
		try {
			if (propertyService.findAllToEdit().contains(property)) {
				isOwner = true;
			}
		} catch (Throwable th) {
		}

		result = new ModelAndView("property/display");
		result.addObject("requestURI", "property/display.do");
		result.addObject("property", property);
		result.addObject("attributes", attributeValues);
		result.addObject("isOwner", isOwner);

		return result;

	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Property> properties;
		Authority auth;
		auth = new Authority();
		auth.setAuthority("AUDITOR");

		try {
			properties = propertyService.findAll();
			result = new ModelAndView("property/list");
			result.addObject("isLessor", false);
			result.addObject("properties", properties);
			result.addObject("requestURI", "property/list.do");
			result.addObject("audits", auditorService.findByPrincipal().getAudits());
		} catch (Throwable th) {
			properties = propertyService.findAll();
			result = new ModelAndView("property/list");
			result.addObject("isLessor", false);
			result.addObject("properties", properties);
			result.addObject("requestURI", "property/list.do");
		}

		return result;

	}

	@RequestMapping(value = "/ownList", method = RequestMethod.GET)
	public ModelAndView listOwn() {
		ModelAndView result;
		Collection<Property> properties;

		properties = propertyService.findAllToEdit();
		result = new ModelAndView("property/list");
		result.addObject("isLessor", true);
		result.addObject("properties", properties);
		result.addObject("requestURI", "property/ownList.do");

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
