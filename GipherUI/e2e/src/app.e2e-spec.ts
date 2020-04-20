import { AppPage } from './app.po';
import { browser, by, element, logging, until } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Gipher Application');
  });


  it('should be redirected to /login page', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(2000);
  });

  it('should be able to register user', () => {
    browser.element(by.id('username')).sendKeys('test1234');
    browser.element(by.id('email')).sendKeys('test1234');
    browser.element(by.id('password')).sendKeys('test1234');
    browser.element(by.css('.register-user')).click();
    browser.driver.sleep(2000);
  });


  it('should be able to login user', () => {
    browser.element(by.id('username')).sendKeys('test1234');
    browser.element(by.id('password')).sendKeys('test1234');
    browser.element(by.css('.login-click')).click();
    browser.driver.sleep(2000);
  });

  it('should be able to save gif to favourite', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.add2FavouriteButton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to get all data from favourite list', () => {
    browser.driver.manage().window().maximize();
    browser.element(by.css('.mat-button-favlist')).click();
    browser.driver.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/favourite');
  });

  it('should be able to open dialogbox to update comment from favlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.updateButton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to save update comment from favlist', () => {
    browser.driver.manage().window().maximize();
    browser.element(by.css(".mat-input")).sendKeys("new coomments");
    browser.driver.sleep(1000);
    browser.element(by.css('.updateCommentdemo')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to delete data from favlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.deleteButton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to get all data from recommendations list', () => {
    browser.driver.manage().window().maximize();
    browser.element(by.css('.mat-button-recommendations')).click();
    browser.driver.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/recommendations');
  });

  it('should be able to logout', () => {
    browser.driver.sleep(500);
    browser.element(by.css('.mat-button-logout')).click();
    browser.driver.sleep(500);
  });

  // afterEach(async () => {
  //   // Assert that there are no errors emitted from the browser
  //   const logs = await browser.manage().logs().get(logging.Type.BROWSER);
  //   expect(logs).not.toContain(jasmine.objectContaining({
  //     level: logging.Level.SEVERE,
  //   } as logging.Entry));
  // });
});
