
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import domain.Attachment;

@Controller
@RequestMapping("/attachment")
public class AttachmentController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditService	auditService;


	// Constructors -------------------------------------------

	public AttachmentController() {
		super();
	}

	// Listing ------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int auditId) {
		ModelAndView result;
		Collection<Attachment> attachments;

		attachments = auditService.findAttachmentsByAudit(auditId);

		result = new ModelAndView("attachment/list");
		result.addObject("attachments", attachments);
		result.addObject("requestURI", "attachment/list.do");

		return result;
	}

}
