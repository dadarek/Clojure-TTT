(ns clojure_ttt.rules
  (use clojure.set))

(def winning-rows
  [ #{1 2 3} #{4 5 6} ]
)

(defn winner? [squares]
  (some
    (fn [x] (subset? x squares))
    winning-rows)
)
