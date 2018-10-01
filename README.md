# vizk8s

Vizualize a K8s cluster

## Overview

FIXME: Write a paragraph about the library/project and highlight its goals.

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

The Clojure package [com.guaranteedrate/ring-proxy "3.0.0"] needs to be downloaded and patched

$ git clone https://github.com/Guaranteed-Rate/ring-proxy.git 

Then do a patch from https://github.com/tailrecursion/ring-proxy/pull/14/commits/97be94dc2d4a93c1810a60019a0e02c2c0f9ef97#diff-3fe6fccb7dc3e851151e2fdc7f13ecee

$ export LEIN_SNAPSHOTS_IN_RELEASE=true
$ lein uberjar
$ lein install



## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
