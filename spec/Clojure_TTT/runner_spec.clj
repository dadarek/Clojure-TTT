(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(defrecord MockPlayer [moves player next-move-pointer]
  Player
  (next-move [this board]
    (do
      (let [result (nth moves @next-move-pointer)]
        (dosync (ref-set next-move-pointer (inc @next-move-pointer)))
       result))))

(defrecord MockUI [counter]
  RunnerUI
  (redraw [this board] (dosync (ref-set counter (inc @counter))))
)

(describe "Game Runner"
  (it "Listens to players"
    (should= '(:x :o :x
               :x :o :o
               :o :x :x)
              (run (MockPlayer. '(1 3 4 8 9) :x (ref 0))
                   (MockPlayer. '(2 5 6 7) :o (ref 0))
                   (MockUI. (ref 0)))))

  (it "Stops when somebody wins"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 2 3) :x (ref 0))
                   (MockPlayer. '(4 5) :o (ref 0))
                   (MockUI. (ref 0)))))

  (it "Doesn't allow duplicate moves"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 3 2) :x (ref 0))
                   (MockPlayer. '(1 4 5) :o (ref 0))
                   (MockUI. (ref 0)))))

  (it "Notifies UI on each turn."
      (def counter (ref 0))
      (run (MockPlayer. '(1 3 2) :x (ref 0))
           (MockPlayer. '(1 4 5) :o (ref 0))
           (MockUI. counter))
      (should= 6 @counter)))

(run-specs)
