(ns vizk8s.view.nodes
  (:require
   [vizk8s.model :as model]))


;(:spec (nth (get-in @vizk8s.model/app-state [:nodes])3))
; (last (:conditions (:status (second (get-in @vizk8s.model/app-state [:nodes])))))

(defn node-panel [app-state]
  (into [:ul]
        (for [{{:keys [name]} :metadata}
              (get-in @vizk8s.model/app-state [:nodes])]
          [:li name])))



