(ns clojure_ttt.generic_minimax)

(defn score [moves context wins? ties? get-child-context-of]
  (let [score-child-moves-of
          (fn [move]
            (score (disj moves move) (get-child-context-of move context) wins? ties? get-child-context-of))
        score-single-move
          (fn [move]
            (cond
              (wins? move context) 1
              (ties? move context) 0
              :else (- (reduce + (score-child-moves-of move)))))]
    (map score-single-move moves)))

