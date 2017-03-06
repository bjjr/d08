
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FeeService;
import domain.Fee;

@Controller
@RequestMapping("/fee")
public class FeeController {

	@Autowired
	private FeeService	feeService;


	public FeeController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Fee fee;

		fee = feeService.findFee();
		result = new ModelAndView("fee/display");
		result.addObject("requestURI", "fee/display.do");
		result.addObject("fee", fee);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Fee fee;

		fee = feeService.findFee();
		Assert.notNull(fee);
		result = createEditModelAndView(fee);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Fee fee, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(fee);
		} else {
			try {
				feeService.save(fee);
				result = new ModelAndView("redirect:display.do");
				result.addObject("message", "fee.commit.ok");
			} catch (Throwable oops) {
				result = createEditModelAndView(fee, "fee.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Fee fee) {
		ModelAndView result;

		result = createEditModelAndView(fee, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Fee fee, String message) {
		ModelAndView result;

		result = new ModelAndView("fee/edit");
		result.addObject("fee", fee);
		result.addObject("message", message);

		return result;
	}

}
