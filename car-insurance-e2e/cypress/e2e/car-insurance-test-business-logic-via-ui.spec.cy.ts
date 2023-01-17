import {CarInsurancePage} from "../pages/car-insurance.page";

describe('Create a car insurance quote', () => {
  const startDate = new Date().toISOString().slice(0, 10);

  let page: CarInsurancePage;

  beforeEach(() => {
    page = new CarInsurancePage();
    page.visit();
  });

  describe('check premium', () => {
    describe('for different bonus/malus levels', () => {
      it('with 50% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-0').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('41.80');
      });
      it('with 40% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-4').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('58.52');
      });
      it('with 30% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-6').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('66.88');
      });
      it('with 20% bonus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-7').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('66.88');
      });
      it('with no bonus and no malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('83.60');
      });
      it('with 20% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Malus-10').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('100.32');
      });
      it('with 40% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Malus-13').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('117.04');
      });
      it('with 70% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Malus-15').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('142.12');
      });
      it('with 100% malus', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Malus-17').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getPremium().contains('167.20');
      });
    });
    describe('for different zip code', () => {
      it('no risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('83.60');
      });
      it('low risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '3334').nextStep();

        page.getPremium().contains('88.00');
      });
      it('high risk zip code', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '9999').nextStep();

        page.getPremium().contains('92.40');
      });

    });
    describe('for different car power', () => {
      it('low power with fix premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 10).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('22.61');
      });
      it('medium power with % premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 100).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('83.60');
      });
      it('high power with fix premium', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 146).nextStep();
        page.contractDetails('2022-01-13', 'Liability', '9').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1000').nextStep();

        page.getPremium().contains('122.08');
      });
    });
  });

  describe('check tax', () => {
    describe('for different fuel types', () => {
      it('for Gasoline', () => {
        page.carDetails('2020-12-01', 'Gasoline', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-0').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('30.96');
      });
      it('for Diesel', () => {
        page.carDetails('2020-12-01', 'Diesel', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-1').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('30.96');
      });
      it('for Hybrid', () => {
        page.carDetails('2020-12-01', 'Hybrid', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-2').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('25.20');
      });
      it('for Electricity', () => {
        page.carDetails('2020-12-01', 'Electricity', 123, 100).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-3').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('0.00');
      });
    });

    describe('for different power values', () => {
      it('for 165 kw', () => {
        page.carDetails('2020-12-01', 'Gasoline', 215, 165).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-0').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('144.00');
      });
      it('for 110 kw', () => {
        page.carDetails('2020-12-01', 'Diesel', 135, 110).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-1').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('46.80');
      });
      it('for 65 kw', () => {
        page.carDetails('2020-12-01', 'Gasoline', 115, 65).nextStep();
        page.contractDetails(startDate, 'Liability', 'Bonus-2').nextStep();
        page.personDetails('Mikey Mouse', '1943-11-03', '1230').nextStep();

        page.getTax().contains('7.20');
      });
    });
  });

});
