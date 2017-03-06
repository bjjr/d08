
package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.AuditorService;
import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditService	auditService;

	@Autowired
	private AuditorService	auditorService;

	private int				propertyId;


	// Constructors -------------------------------------------

	public AuditAuditorController() {
		super();
	}

	// Creating -----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {
		ModelAndView result;
		Audit audit;

		audit = auditService.create(propertyId);
		setPropertyId(propertyId);
		result = createEditModelAndView(audit);

		return result;
	}

	// Listing ------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Audit> audits;
		Auditor auditor;
		Boolean isAuditor;

		auditor = auditorService.findByPrincipal();
		audits = auditor.getAudits();
		isAuditor = true;

		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("isAuditor", isAuditor);
		result.addObject("requestURI", "audit/auditor/list.do");

		return result;
	}

	// Edition -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int auditId, @RequestParam int propertyId) {
		ModelAndView result;
		Audit audit;

		audit = auditService.findOneToEdit(auditId, propertyId);
		setPropertyId(propertyId);

		result = createEditModelAndView(audit);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "publish")
	public ModelAndView publish(Audit audit, BindingResult binding) {
		ModelAndView result;

		audit = auditService.reconstruct(audit, getPropertyId(), binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(audit);
		} else {
			try {
				auditService.save(audit);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(audit, "audit.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Audit audit, BindingResult binding) {
		ModelAndView result;

		try {
			auditService.delete(audit);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(audit, "audit.commit.error");
		}

		return result;
	}

	// Save as draft -----------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveAsDraft")
	public ModelAndView saveAsDraft(Audit audit, BindingResult binding) {
		ModelAndView result;

		audit = auditService.reconstruct(audit, propertyId, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(audit);
		} else {
			try {
				auditService.saveAsDraft(audit);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(audit, "audit.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods -------------------------------------

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	protected ModelAndView createEditModelAndView(Audit audit) {
		ModelAndView result;

		result = createEditModelAndView(audit, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Audit audit, String message) {
		ModelAndView result;
		Collection<Audit> audits;

		audits = auditService.findAll();

		result = new ModelAndView("audit/edit");
		result.addObject("audit", audit);
		result.addObject("audits", audits);
		result.addObject("message", message);

		return result;
	}
}
