(ns vizk8s.view.navbar
  (:require
   [vizk8s.model :as model]))


(defn navbar [app-state]
  [:nav.navbar
   [:ul.nav-list
    [:li
     [:a
      {:href "#/"}
      [:img
       {:src "img/logotext.svg"}]
      [:span
       {:style {:font-family "fantasy"}}
       " Whip"]]]
    [:ul.nav-list
     {:style {:float "right"}}
     [:li
      [:a {:href "#/"} "About"]]
     [:li
      (if-let [username (:username @model/app-state)]
        [:a {:href "#/settings"} username]
        [:a {:href "#/login"} "Login"])]]]])

