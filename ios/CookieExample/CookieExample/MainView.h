//
//  MainView.h
//  CookieExample
//
//  Created by Daniel Zlotin on 23/05/2017.
//  Copyright © 2017 Wix.com. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MainView : UIView

-(instancetype)initWithListener:(id)listener selector:(SEL)selector;

-(void)setFirstLabel:(NSString*)firstLabel;
-(void)setSecondLabel:(NSString*)secondLabel;

@end
