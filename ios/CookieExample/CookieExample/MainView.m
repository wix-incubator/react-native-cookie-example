//
//  MainView.m
//  CookieExample
//
//  Created by Daniel Zlotin on 23/05/2017.
//  Copyright © 2017 Wix.com. All rights reserved.
//

#import "MainView.h"

@implementation MainView {
    id _listener;
    SEL _selector;
    BOOL _measured;
    UIProgressView* _progress;
    UILabel* _firstLabel;
    UILabel* _secondLabel;
    UIButton* _clearCookiesBtn;
}

-(instancetype)initWithListener:(id)listener selector:(SEL)selector {
    self = [super init];
    _listener = listener;
    _selector = selector;
    
    [self setBackgroundColor:[UIColor whiteColor]];
    [self createFirstLabel];
    [self createSecondLabel];
    [self createProgress];
    [self createClearCookiesBtn];
    
    return self;
}

-(void)createFirstLabel {
    _firstLabel = [[UILabel alloc] init];
    [_firstLabel sizeToFit];
    [self addSubview:_firstLabel];
}

-(void)createSecondLabel {
    _secondLabel = [[UILabel alloc] init];
    [_secondLabel sizeToFit];
    [self addSubview:_secondLabel];
}

-(void)createProgress {
    _progress = [[UIProgressView alloc] init];
    [self addSubview:_progress];
    [_progress sizeToFit];
}

-(void)layoutSubviews {
    [super layoutSubviews];
    if(!_measured) {
        _measured = true;
        CGFloat centerX = [[UIApplication sharedApplication]keyWindow].frame.size.width/2.0;
        [_firstLabel setCenter:CGPointMake(centerX, 150)];
        [_secondLabel setCenter:CGPointMake(centerX, 200)];
        [_clearCookiesBtn setCenter:CGPointMake(centerX, 300)];
    }
}

-(void)setFirstLabel:(NSString *)firstLabel {
    [_firstLabel setText:firstLabel];
    [_firstLabel sizeToFit];
}

-(void)setSecondLabel:(NSString *)secondLabel {
    [_secondLabel setText:secondLabel];
    [_secondLabel sizeToFit];
}

-(void)createClearCookiesBtn {
    _clearCookiesBtn = [[UIButton alloc] init];
    [_clearCookiesBtn setTitle:@"Clear All Cookies" forState:UIControlStateNormal];
    [_clearCookiesBtn setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
    [_clearCookiesBtn addTarget:_listener action:_selector forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:_clearCookiesBtn];
    [_clearCookiesBtn sizeToFit];
}

@end
