# tesco-delivery

Tesco has a fleet of vehicles to deliver orders to the customer. Assigning the right set of orders to different sized vehicles is crucial for efficient delivery of orders. Different vehicle can fit different container sizes.

Given c containers, along with their volumes [l,b,h], catalogue of product with its volume requirement (l,b,h) and an order with p products and its quantity.

Example:
Containers:
SMALL -> id=1, length=10, breadth=20, height=30
MEDIUM -> id=2, length=50, breadth=60, height=70
LARGE -> id=3, length=100, breadth=200, height=300

Product:
productId=1, length=2, breadth=4, height=10
productId=2, length=10, breadth=30, height=4
productId=3, length=5, breadth=6, height=7

Order:
productId=1, quantity=3
productId=3, quantity=7

Determine if that order fits in any of the given c containers and return the ID of the container that can be used.
Given n orders, determine the maximum number of orders that can be fit into a particular container.