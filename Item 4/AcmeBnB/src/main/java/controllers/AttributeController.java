
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeService;
import domain.Attribute;

@Controller
@RequestMapping("/attribute")
public class AttributeController extends AbstractController {

	// Services ---------------------------------------------

	@Autowired
	private AttributeService	attributeService;


	// Constructors -----------------------------------------

	public AttributeController() {
		super();
	}

	// Listing ----------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Attribute> attributes;

		attributes = attributeService.findAll();

		res = new ModelAndView("attribute/list");

		res.addObject("attributes", attributes);

		return res;

	}

	// Creating ---------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Attribute attribute;

		attribute = attributeService.create();

		res = createEditModelAndView(attribute);

		return res;
	}

	// Editing ----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attributeId) {
		ModelAndView res;
		Attribute attribute;

		attribute = attributeService.findOne(attributeId);

		res = createEditModelAndView(attribute);

		return res;
	}

	// Saving -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Attribute attribute, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = createEditModelAndView(attribute);
		} else {
			try {
				attributeService.save(attribute);
				res = new ModelAndView("redirect:list.do");
			} catch (Throwable th) {
				res = createEditModelAndView(attribute, "attribute.commit.error");
			}
		}

		return res;
	}

	// Deleting ---------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Attribute attribute, BindingResult binding) {
		ModelAndView res;

		try {
			attributeService.delete(attribute);
			res = new ModelAndView("redirect:list.do");
		} catch (Throwable th) {
			res = createEditModelAndView(attribute, "attribute.commit.error");
		}

		return res;
	}

	// Ancillary methods ------------------------------------

	protected ModelAndView createEditModelAndView(Attribute attribute) {
		ModelAndView res;

		res = createEditModelAndView(attribute, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(Attribute attribute, String message) {
		ModelAndView res;

		res = new ModelAndView("attribute/edit");

		res.addObject("attribute", attribute);
		res.addObject("message", message);

		return res;
	}
}
