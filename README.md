# .interpolate()
## Lagrange interpolation visualizer

[Lagrange interpolation](https://en.wikipedia.org/wiki/Lagrange_polynomial) is a mathematical method for creating a polynomial function whose graphs passes through a fixed collection of points. The result is the polynomial function of least degree that passes through the given points, known as a Lagrange polynomial. This visualizer allows you to manipulate the point set of a Lagrange polynomial in real-time, and observe how the polynomial changes. It also has a "free" mode where the points will move on their own.

Please note that this project is still under development. More features and a nicer UI to come!

## Options

* **# of polynomials**: how many Lagrange polynomials to graph, 1 or 2.
* **Interpolation points**: how many points should be used to form each polynomial, from 2 to 9. Note that Lagrange polynomials have degree one less than the number of points used.
* **Graphing style**: Line or filled. Line graphs the polynomial(s) normally, filled shows either the area beneath the graph (if graphing one polynomial), or the area between the graphs (if graphing two polynomials).
* **Interpolation mode**: static or free. Static allows you to drag the points around and watch the graph change. Free will randomly shift the points within certain parameters, often producing interesting visuals.
* **Show axes**: whether to hide or show the x- and y-axes. The x-axis crosses the center of the viewing area, while the y-axis is the left edge. Note that translating a set of points together simply translates the Lagrange polynomial by the same amount, so the position of the origin is essentially irrelevant.
* **Show points**: whether to show the point set being used to create the Lagrange polynomial. When in "static" mode, this box is automatically checked.

## Credits

Created by [Louis Webb](https://tangledwebgames.com) with libGDX, licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).