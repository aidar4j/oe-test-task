# oe-test-task

Bidder bot is simple implemented bot, that can participate in auctions and possibly win. Yes bot wins in most of cases, but in loses anyway sometimes. 

## The main algorithm of winning in bidding is:

1. Evenly split cash among the sets. For example if cash is 100 and quantity is 10, then sets are 5 and evenly split cash is 20 for each set.
2. First bid is random value from evenly split cash (value from 1.)
3. Collect data of previous winning(!!) bids
4. Second and next bids are average value from all winning bids plus 40% of evenly split cash (value from 1.)
5. That´s it!

## What can be improved?

1. Change algorithm to more effective one.
2. Implement Strategy pattern. Strategies can change the behavior of bot, according to opponent bids
