(ns vizk8s.view.nodes
  (:require
   [vizk8s.model :as model]))


(defn node-panel [app-state]
  (into [:ul]
        (for [{{:keys [name]} :metadata}
              (get-in @vizk8s.model/app-state [:nodes])]
          [:li name])))



