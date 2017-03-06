
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
	private AttributeService	attributeService;


	//	@Autowired
	//	private AttributeValueService	attributeValueService;

	// Tests ---------------------------------------

	@Test
	public void testCreate1() {
		Attribute a;
		a = attributeService.create();

		Assert.isTrue(a.getName().equals(""));
		Assert.notNull(a.getName());

	}

	//	@Test
	//	public void testSave() {
	//		Attribute attribute, saved;
	//
	//		attribute = attributeService.create();
	//
	//		attribute.setName("prueba test");
	//
	//		saved = attributeService.save(attribute);
	//
	//		Assert.isTrue(saved.getId() != 0);
	//		Assert.isTrue(saved.getName().equals(attribute.getName()));
	//		Assert.isTrue(saved.getAttributeValues().equals(attribute.getAttributeValues()));
	//
	//	}

	//	@Test
	//	public void testAddAttibuteValue() {
	//		Attribute attribute;
	//		AttributeValue attributeValue;
	//
	//		attribute = attributeService.create();
	//
	//		attributeService.addAttributeValue(attribute, attributeValue);
	//
	//		Assert.notEmpty(attribute.getAttributeValues());
	//		Assert.notNull(attributeValue);
	//	}
	//
	//	@Test
	//	public void testRemoveAttibuteValue() {
	//		Attribute attribute;
	//		AttributeValue attributeValue;
	//
	//		attribute = attributeService.create();
	//
	//		attributeService.addAttributeValue(attribute, attributeValue);
	//
	//		Assert.notEmpty(attribute.getAttributeValues());
	//		Assert.notNull(attributeValue);
	//
	//		attributeService.removeAttributeValue(attribute, attributeValue);
	//
	//		Assert.isTrue(attribute.getAttributeValues().isEmpty());
	//		Assert.isNull(attributeValue);
	//	}

}
