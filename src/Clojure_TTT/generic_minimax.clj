(ns clojure_ttt.generic_minimax)


(defn score [moves context wins? ties? get-child-context-of]
  (let [child-moves-of (fn [move] (disj moves move))
        score-move (fn [move]
                      (if (wins? move context) 1
                      (if (ties? move context) 0
                      (- (reduce +
                            (score
                               (child-moves-of move)
                               (get-child-context-of move context)
                               wins?
                               ties?
                               get-child-context-of))))))]
    (map score-move moves)))
      
