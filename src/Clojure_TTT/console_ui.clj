(ns clojure_ttt.console_ui
  (:use clojure_ttt.runner)
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
    (let [symbol-for #(cond (= :x %) "x" (= :o %) "o" :default " ")]
      (do
        (println (apply str (interpose "|" (map symbol-for (take 3 board)))))
        (println (apply str (interpose "|" (map symbol-for (drop 3 (take 6 board))))))
        (println (apply str (interpose "|" (map symbol-for (drop 6 board)))))))))

