# .interpolate()
## Lagrange interpolation visualizer

[Lagrange interpolation](https://en.wikipedia.org/wiki/Lagrange_polynomial) is a mathematical method for creating a polynomial function whose graphs passes through a fixed collection of points. The result is the polynomial function of least degree that passes through the given points, known as a Lagrange polynomial. This visualizer allows you to manipulate the point set of a Lagrange polynomial in real-time, and observe how the polynomial changes. It also has a "free" mode where the points will move on their own.

## Main Options

* **# of polynomials**: how many Lagrange polynomials to graph, 1 or 2.
* **Interpolation mode**: static or free. Static allows you to drag the points around and watch the graph change. Free will randomly shift the points within certain parameters, often producing interesting visuals.
* **Show axes**: whether to hide or show the x- and y-axes. The x-axis crosses the center of the viewing area, while the y-axis is the left edge. Note that translating a set of points together simply translates the Lagrange polynomial by the same amount, so the position of the origin is essentially irrelevant.
* **Show points**: whether to show the point set being used to create the Lagrange polynomial. When in "static" mode, this box is automatically checked.
* **Graphing style**: Line or filled. Line graphs the polynomial(s) normally, filled shows either the area beneath the graph (if graphing one polynomial), or the area between the graphs (if graphing two polynomials).

## Polynomial Options

Each polynomial has its options set separately, with additional options for free mode.

* **Interpolation points**: how many points should be used to form each polynomial, from 2 to 9. Note that Lagrange polynomials have degree one less than the number of points used.
* **Speed (free mode only)**: how quickly the polynomial will change.
* **Motion (free mode only)**: algorithm for determining how the polynomial changes over time.  The "bands" option moves each point in the generating set for the polynomial within a vertical band, while "random" will move each point to an arbitrary new point.  Lagrange polynomial behavior is most erratic when two of the generating points have close *x* values, and the sign of the polynomial flips every time one point crosses another.  Since in the "bands" option, the interpolation points never cross, it produces much more stable motion than the "random" option.

## Credits

Created by [Louis Webb](https://tangledwebgames.com) with libGDX, licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).