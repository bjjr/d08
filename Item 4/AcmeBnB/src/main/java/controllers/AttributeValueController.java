
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeService;
import services.AttributeValueService;
import domain.Attribute;
import domain.AttributeValue;

@Controller
@RequestMapping("/attributeValue")
public class AttributeValueController extends AbstractController {

	// Services -----------------------------------------

	@Autowired
	private AttributeValueService	attributeValueService;

	@Autowired
	private AttributeService		attributeService;

	private int						propertyId;


	// Constructors -------------------------------------

	public AttributeValueController() {
		super();
	}

	// Creating ------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {
		ModelAndView res;
		AttributeValue attributeValue;

		attributeValue = attributeValueService.create();
		setPropertyId(propertyId);

		res = createEditModelAndView(attributeValue);

		return res;
	}

	// Editing -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attributeValueId, @RequestParam int propertyId) {
		ModelAndView res;
		AttributeValue attributeValue;

		attributeValue = attributeValueService.findOneToEdit(attributeValueId, propertyId);
		setPropertyId(propertyId);

		res = createEditModelAndView(attributeValue);

		return res;
	}

	// Saving ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(AttributeValue attributeValue, BindingResult binding) {
		ModelAndView res;

		attributeValue = attributeValueService.reconstruct(attributeValue, getPropertyId(), binding);

		if (binding.hasErrors()) {
			res = createEditModelAndView(attributeValue);
		} else {
			try {
				attributeValueService.save(attributeValue);
				res = new ModelAndView("redirect:/property/display.do?propertyId=" + getPropertyId());
			} catch (Throwable th) {
				res = createEditModelAndView(attributeValue, "misc.commit.error");
			}
		}

		return res;
	}

	// Deleting ----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(AttributeValue attributeValue, BindingResult binding) {
		ModelAndView res;

		try {
			attributeValueService.delete(attributeValue);
			res = new ModelAndView("redirect:/property/display.do?propertyId=" + getPropertyId());
		} catch (Throwable th) {
			res = createEditModelAndView(attributeValue, "misc.commit.error");
		}

		return res;
	}

	// Ancillary methods -------------------------------------

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	protected ModelAndView createEditModelAndView(AttributeValue attributeValue) {
		ModelAndView res;

		res = createEditModelAndView(attributeValue, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(AttributeValue attributeValue, String message) {
		ModelAndView res;
		Collection<Attribute> attributes;

		res = new ModelAndView("attributevalue/edit");
		attributes = attributeService.findAll();

		res.addObject("attributeValue", attributeValue);
		res.addObject("message", message);
		res.addObject("attributes", attributes);

		return res;
	}

}
