package pkgEmpty;
import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

public class TestRetirement {

		@Test
		public void TestPVPMT() {
			double dAnnualReturnWorking = 0.07;
			int iYearsToWork = 40;
			int iYearsRetired = 20; 
			double dAnnualReturnRetired = 0.02;
			double dRequiredIncome = 10000;
			double dMonthlySSI = 2642;

			double GivenPay = -554.13;
			double GivenTotal = 1454485.55;


			Retirement Ret = new Retirement(iYearsToWork, dAnnualReturnWorking, 
					iYearsRetired, dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);
			
			double PV = Ret.totalAmountSaved();
			double PMT = Ret.amountToSave();
			
			assertEquals(GivenTotal, PV, 0.01);
			assertEquals(GivenPay, PMT, 0.01);
			
			System.out.println(PV);
			System.out.println(PMT);
		}
}
