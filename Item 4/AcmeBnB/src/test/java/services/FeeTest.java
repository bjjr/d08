
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Fee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FeeTest extends AbstractTest {

	// Service under test ------------------------------------

	@Autowired
	private FeeService	feeService;


	// Tests -------------------------------------------------

	@Test
	public void testSave() {
		Fee fee;
		Fee saved;

		fee = new Fee();
		fee.setValue(2.5);

		saved = feeService.save(fee);

		feeService.flush();

		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getValue() == fee.getValue());
	}

	@Test
	public void testFindFee() {
		Fee fee;

		fee = feeService.findFee();

		Assert.isTrue(fee.getValue() == 1.0);
	}
}
