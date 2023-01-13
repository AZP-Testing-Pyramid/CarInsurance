export class CarInsurancePage {
  public visit(): void {
    cy.visit('/');
  }

  carDetails(registrationDate: string, fuelType: string, co2Emissions: number, power: number) {
    cy.get('#firstRegistration').type(registrationDate);
    cy.get('mat-select[formControlName=fuelType]').click().get('mat-option').contains(fuelType).click();
    cy.get('#co2Emissions').type(co2Emissions.toString());
    cy.get('#power').type(power.toString());

    return {
      nextStep: function () {
        cy.get('#forwardToContract').contains('Next').click();
      },
      setPower(power: number) {
        cy.get('#power').clear();
        cy.get('#power').type(power.toString());
      }
    }
  }

  contractDetails(startDate: string, coverage: string, bonusMalusLevel: string) {
    cy.get('#startDate').type(startDate);
    cy.get('mat-select[formControlName=coverage]').click().get('mat-option').first().click();
    cy.get('mat-select[formControlName=bonusMalusLevel]').click().get('mat-option').contains(bonusMalusLevel).click();

    return {
      nextStep: function () {
        cy.get('#forwardToPerson').contains('Next').click();
      },
      previousStep: function () {
        cy.get('#backToCar').contains('Back').click();
      }
    }
  }

  personDetails(name: string, dayOfBirth: string, zipCode: string) {
    cy.get('#name').type(name);
    cy.get('#dayOfBirth').type(dayOfBirth);
    cy.get('#zipCode').type(zipCode);

    return {
      nextStep: function () {
        cy.get('#forwardToSummary').contains('Next').click();
      },
      previousStep: function () {
        cy.get('#backToContract').contains('Back').click();
      }

    }
  }

  getTax() {
    return cy.get('#tax');
  }

  getPremium() {
    return cy.get('#premium');
  }

  summary() {
    return {
      previousStep: function () {
        cy.get('#backToPerson').contains('Back').click();
      },
      reset: function () {
        cy.get('#resetQuote').contains('Reset').click();
      },
      submit: function () {
        cy.get('#submitQuote').contains('Submit').click();
      }
    }
  }
}
