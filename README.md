# Approach used

The approached used is of a rules engine. 
All discounts rules implement the apply interface, which takes the list of items to process.
If a discount is to be applied to an item, one should set the discount type on that item.
In the future, if an item can have multiple discounts, the discount property could be a list of discounts.

# What would/should I have done

While trying to solve the problem, I was thinking of many solutions (too much thinking perhaps ?), and when I went with the current one, I realised one problem.

I think a better solution would be to use Discount as a decorator of an Item, and the Basket should hold the list of those discounts applied.

Any newly implemented discount rule would then be a subclass of this abstract Discount class.

Then, in the same rules engine (DiscountCalculator) we could iterate through those discount rules, and have the logic in the overloaded apply method.

