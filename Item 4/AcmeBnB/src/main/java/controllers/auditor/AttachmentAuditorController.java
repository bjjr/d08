
package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	private int					auditId;


	// Constructors -------------------------------------------

	public AttachmentAuditorController() {
		super();
	}

	// Creating -----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int auditId) {
		ModelAndView result;
		Attachment attachment;

		attachment = attachmentService.create();
		result = createEditModelAndView(attachment);
		result.addObject("auditId", auditId);
		setAuditId(auditId);

		return result;
	}

	// Edition -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int attachmentId, @RequestParam int auditId) {
		ModelAndView result;
		Attachment attachment;

		attachment = attachmentService.findOneToEdit(attachmentId, auditId);
		setAuditId(auditId);

		result = createEditModelAndView(attachment);
		result.addObject("auditId", auditId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Attachment attachment, BindingResult binding) {
		ModelAndView result;

		attachment = attachmentService.reconstruct(attachment, getAuditId(), binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(attachment);
		} else {
			try {
				attachmentService.save(attachment);
				result = new ModelAndView("redirect:/attachment/list.do?auditId=" + getAuditId());
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
			result = new ModelAndView("redirect:/attachment/list.do?auditId=" + getAuditId());
		} catch (Throwable oops) {
			result = createEditModelAndView(attachment, "attachment.commit.error");
		}

		return result;
	}

	// Ancillary methods -------------------------------------

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	protected ModelAndView createEditModelAndView(Attachment attachment) {
		ModelAndView result;

		result = createEditModelAndView(attachment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Attachment attachment, String message) {
		ModelAndView result;
		Collection<Attachment> attachments;

		attachments = attachmentService.findAll();

		result = new ModelAndView("attachment/edit");
		result.addObject("attachment", attachment);
		result.addObject("attachments", attachments);
		result.addObject("message", message);
		result.addObject("auditId", getAuditId());

		return result;
	}

}
