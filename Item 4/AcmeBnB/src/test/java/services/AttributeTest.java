
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attribute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AttributeTest extends AbstractTest {

	// Service under test --------------------------

	@Autowired
	private AttributeService		attributeService;

	@Autowired
	private AttributeValueService	attributeValueService;


	// Tests ---------------------------------------

	@Test
	public void testCreate1() {
		Attribute a;
		a = attributeService.create();

		Assert.isTrue(a.getName().equals(""));
		Assert.notNull(a.getName());

	}

	@Test
	public void testSave() {
		Attribute attribute, saved;

		attribute = attributeService.create();

		attribute.setName("prueba test");

		saved = attributeService.save(attribute);
		attributeService.flush();

		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getName().equals(attribute.getName()));
	}
	
	/*
	 * Check if an attribute gets deleted
	 */

	@Test(expected = IllegalAccessException.class)
	public void testDelete1() {
		Attribute attribute, saved;
		int id;

		attribute = attributeService.create();
		attribute.setName("Test");

		saved = attributeService.save(attribute);
		attributeService.flush();
		id = saved.getId();

		attributeService.delete(saved);
		attributeService.flush();

		attributeService.findOne(id);
	}
	
	/*
	 * Check if attribute values related to an attribute get deleted
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testDelete2() {
		Attribute attribute;

		attribute = attributeService.findOne(21);

		attributeService.delete(attribute);

		attributeService.flush();
		attributeValueService.flush();

		attributeValueService.findOne(44);
	}

}
