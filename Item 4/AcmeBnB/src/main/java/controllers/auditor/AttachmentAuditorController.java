
package controllers.auditor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttachmentService;
import controllers.AbstractController;
import domain.Attachment;

@Controller
@RequestMapping("/attachment/auditor")
public class AttachmentAuditorController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AttachmentService	attachmentService;


	// Constructors -------------------------------------------

	public AttachmentAuditorController() {
		super();
	}

	// Creating -----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Attachment attachment;

		attachment = attachmentService.create();
		result = createEditModelAndView(attachment);

		return result;
	}

	// Edition -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attachmentId) {
		ModelAndView result;
		Attachment attachment;

		attachment = attachmentService.findOne(attachmentId);
		Assert.notNull(attachment);
		result = createEditModelAndView(attachment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Attachment attachment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(attachment);
		} else {
			try {
				attachmentService.save(attachment);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(attachment, "attachment.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Attachment attachment, BindingResult binding) {
		ModelAndView result;

		try {
			attachmentService.delete(attachment);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(attachment, "attachment.commit.error");
		}

		return result;
	}

	// Ancillary methods -------------------------------------

	protected ModelAndView createEditModelAndView(Attachment attachment) {
		ModelAndView result;

		result = createEditModelAndView(attachment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Attachment attachment, String message) {
		ModelAndView result;
		Collection<Attachment> attachments;

		attachments = attachmentService.findAll();

		result = new ModelAndView("audit/edit");
		result.addObject("attachment", attachment);
		result.addObject("attachments", attachments);
		result.addObject("message", message);

		return result;
	}

}
