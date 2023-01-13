import {CarInsurancePage} from "../pages/car-insurance.page";

describe('create a simple quote', () => {
  const startDate = new Date().toISOString().slice(0, 10);

  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  it('for ..', () => {
    const startDate = new Date().toISOString().slice(0, 10);

    page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
    page.contractDetails(startDate, 'Liability', 'Bonus-0').nextStep();
    page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

    page.getTax().contains('30.96');
    page.getPremium().contains('41.80');
  });

 });
