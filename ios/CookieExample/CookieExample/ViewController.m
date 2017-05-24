//
//  ViewController.m
//  CookieExample
//
//  Created by Daniel Zlotin on 23/05/2017.
//  Copyright © 2017 Wix.com. All rights reserved.
//

#import "ViewController.h"
#import "MainView.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view = [[MainView alloc]initWithListener:self selector:@selector(onClickClearAllCookies)];
    
    [self makeRequest];
}

-(void)makeRequest {
    NSURL* url = [NSURL URLWithString:@"https://stark-atoll-33661.herokuapp.com/cookie.php"];
    NSString* result1 = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    if(!result1) {
        result1 = @"NULL!";
    }
    [((MainView*)self.view) setFirstLabel:result1];
    
    NSString* result2 = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
    if(!result2) {
        result2 = @"NULL!";
    }
    [((MainView*)self.view) setSecondLabel:result2];
}

-(void)onClickClearAllCookies {
    NSHTTPCookieStorage *cookieStorage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    for (NSHTTPCookie *each in cookieStorage.cookies) {
        [cookieStorage deleteCookie:each];
    }
}

@end
