(ns clojure_ttt.console_ui
  (:use clojure_ttt.runner)
  (:use clojure_ttt.game_loop)
  (:use clojure_ttt.human_player))

(defrecord ConsoleUI []
  NextMoveUI
  (get-next-move [_]
    (let [is-valid #(and (not= nil %) (> 10 %) (< 0 %))
          try-read #(try (Integer/parseInt (read-line))
                    (catch NumberFormatException e nil)) ]
    (loop []
      (print "Please select a square: ")
      (flush)
      (let [result (try-read)]
        (if (is-valid result)
          result
          (recur))))))

  RunnerUI
  (redraw [_ board]
    (let [symbol-for #(cond (= :x %) "x" (= :o %) "o" :default " ")
          row-1 (take 3 board)
          row-2 (drop 3 (take 6 board))
          row-3 (drop 6 board)
          format-row #(apply str (interpose "|" (map symbol-for %)))]
      (do
        (println (format-row row-1))
        (println (format-row row-2))
        (println (format-row row-3)))))
  LoopUI
  (play-again? [_] 
    (loop [result nil]
      (if (or (= "y" result) (= "n" result))
        (= "y" result)
        (recur (read-line))))))

