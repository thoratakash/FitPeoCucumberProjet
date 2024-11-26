@all
Feature: Revenue Calculator Functionality
  As a user,
  I want to interact with the Revenue Calculator,
  So that I can adjust values and validate calculations.

  Background:
    Given User opens the web browser
    And User navigates to the FitPeo Homepage
    And User navigates to the Revenue Calculator page

  @RevenueCalculatorPage @smoke @regression
  Scenario: Validate Revenue Calculator Page Load
    Then User should see the Revenue Calculator page loaded successfully

  @Slider @smoke @regression
  Scenario: Adjust the slider and validate its value
    When User scrolls down to the slider section
    And User adjusts the slider to set its value to 820
    Then The bottom text field value should update to "820"

  @ValidateSlider @smoke @regression
  Scenario: Update the text field and validate slider position
    When User clicks on the text field associated with the slider
    And User enters the value 560 in the text field
    Then The slider position should update to reflect the value 560
    

  @CPTCodes @smoke @regression
  Scenario: Select CPT codes and validate total reimbursement
    When User scrolls down to the slider section
    And User adjusts the slider to set its value to 820
    And User selects the checkboxes for:
      |CheckBox01| CPT-99091 |
      |CheckBox02| CPT-99453 |
      |CheckBox03| CPT-99454 |
      |CheckBox04| CPT-99474 |
    Then The header displaying Total Recurring Reimbursement for all Patients Per Month "$110700"

    