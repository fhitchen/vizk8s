(ns vizk8s.server-handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response]
            [tailrecursion.ring-proxy :refer [wrap-proxy]]))
            ;[puppetlabs.ring-middleware.core :refer [wrap-proxy]]))
            

;; If you are new to using Clojure as an HTTP server please take your
;; time and study ring and compojure. They are both very popular and
;; accessible Clojure libraries.

;; --> https://github.com/ring-clojure/ring
;; --> https://github.com/weavejester/compojure

(defroutes app-routes
  ;; NOTE: this will deliver all of your assets from the public directory
  ;; of resources i.e. resources/public
  (route/resources "/" {:root "public"})
  ;; NOTE: this will deliver your index.html
  (GET "/" [] (-> (response/resource-response "index.html" {:root "public"})
                   (response/content-type "text/html")))
  (ANY "/hello" [] "Hello World there!")
  (route/not-found "Not Found"))

;; NOTE: wrap reload isn't needed when the clj sources are watched by figwheel
;; but it's very good to know about
;; (def dev-app (wrap-reload (wrap-defaults #'app-routes site-defaults)))

(def dev-app (-> app-routes
                 (wrap-defaults site-defaults)
                 ;; Use a django API on a different localhost port:
                 (wrap-proxy "/api/" "http://localhost:8001/api")
                 (wrap-proxy "/apis/" "http://localhost:8001/apis")
                 ;; Use the django static-files if you need for eg the admin site:
                 (wrap-proxy "/static" "http://localhost:8000/static")
                 ;; Proxy requests to a third-party API without CORS enabled:
                 (wrap-proxy "/pq" "http://third.party.com/pq")))
