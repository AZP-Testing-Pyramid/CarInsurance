import {CarInsurancePage} from "../pages/car-insurance.page";

describe('Create a car insurance quote', () => {
  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  describe('with happy path', () => {
    it('enter values to calculate the highest insurance premium and tax rate', () => {
      page.carDetails('2020-12-01', 'Gasoline', 123, 147).nextStep();
      page.contractDetails('2022-01-13', 'Liability', 'Malus-17').nextStep();
      page.personDetails('Mikey Mouse', '1943-11-03', '9000').nextStep();

      page.getTax().contains('64.80');
      page.getPremium().contains('277.20');
    });
  });

  describe('with other navigation paths', () => {
    it('adapting the power after finishing', () => {
      let carDetailComponent = page.carDetails('2020-12-01', 'Gasoline', 123, 147);
      carDetailComponent.nextStep();

      let contractDetailComponent = page.contractDetails('2022-01-13', 'Liability', 'Malus-17');
      contractDetailComponent.nextStep();

      let insuredPersonComponent = page.personDetails('Mikey Mouse', '1943-11-03', '9000');
      insuredPersonComponent.nextStep();

      let summaryComponent = page.summary();
      page.getTax().contains('64.80');
      page.getPremium().contains('277.20');

      summaryComponent.previousStep();
      insuredPersonComponent.previousStep();
      contractDetailComponent.previousStep();
      carDetailComponent.setPower(100);

      carDetailComponent.nextStep();
      contractDetailComponent.nextStep();
      insuredPersonComponent.nextStep();
      page.getTax().contains('30.96');
      page.getPremium().contains('184.80');
    });

    it('reseting all inputs after finishing', () => {
      let carDetailComponent = page.carDetails('2020-12-01', 'Gasoline', 123, 147);
      carDetailComponent.nextStep();

      let contractDetailComponent = page.contractDetails('2022-01-13', 'Liability', 'Malus-17');
      contractDetailComponent.nextStep();

      let insuredPersonComponent = page.personDetails('Mikey Mouse', '1943-11-03', '9000');
      insuredPersonComponent.nextStep();

      let summaryComponent = page.summary();

      summaryComponent.reset();
      cy.get('#co2Emissions').should('have.text', '');
      cy.get('#power').should('have.text', '');
    });
  });


});
