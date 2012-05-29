(ns clojure_ttt.board_utilities-spec
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(def full-board '(:x :o :x
                  :o :o :x
                  :x :x :o))

(def empty-board '(nil nil nil
                   nil nil nil
                   nil nil nil))

(def six-nine-empty '(:o :x :x
                      :x :o nil
                      :x :o nil))

(describe "Board Utilities"
  (context "Player squares"
    (it "finds player x squares"
      (should= #{1 2 8} (player-squares '(:x :x nil
                                        nil nil :o
                                        nil :x nil)
                                       :x)))

    (it "finds player o squares"
      (should= #{5 9} (player-squares '(:x :x nil
                                        nil :o nil
                                        nil :x :o)
                                       :o))))

  (context "Taking squares"
    (it "Appends player to board on correct square"
      (let [context { :player :x
                      :board '(:o :x nil
                               :x :o nil
                               :x :o nil)}
            expected-board   '(:o :x :x
                               :x :o nil
                               :x :o nil)]
        (should= expected-board (take-square 3 context))))

    (it "Takes into consideration both players"
      (let [context { :player :o
                      :board '(:o :x :x
                               :x :o nil
                               :x :o nil)}
            expected-board   '(:o :x :x
                               :x :o :o
                               :x :o nil)]
        (should= expected-board (take-square 6 context)))))

  (context "Empty squares"
    (it "Finds empty squares"
      (should= #{6 9} (get-empty-squares six-nine-empty)))

    (it "Recognizes an empty board"
      (should= (set (range 1 10)) (get-empty-squares empty-board)))

    (it "Recognizes a full board"
      (should= #{} (get-empty-squares full-board))))

  (context "Full"
    (it "Knows a non-full board"
      (should-not (full? six-nine-empty)))

    (it "Knows an empty board"
      (should-not (full? empty-board)))

    (it "Knows a full board"
      (should (full? full-board)))))

(run-specs)
