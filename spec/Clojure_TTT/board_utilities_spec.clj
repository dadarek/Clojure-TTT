(ns clojure_ttt.board_utilities-spec
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(describe "Board Utilities"
  (context "Player squares"
    (it "finds player x squares"
      (should= #{1 2 8} (player-squares '(:x :x nil
                                        nil nil :o
                                        nil :x nil)
                                       :x))
    )

    (it "finds player o squares"
      (should= #{5 9} (player-squares '(:x :x nil
                                        nil :o nil
                                        nil :x :o)
                                       :o))
    )
  )

  (context "Taking squares"
    (it "Appends player to board on correct square"
      (let [context { :player :x
                      :board '(:o :x nil
                               :x :o nil
                               :x :o nil)}
            expected-board   '(:o :x :x
                               :x :o nil
                               :x :o nil)]
        (should= expected-board (take-square 3 context))
      )
    )

    (it "Takes into consideration both players"
      (let [context { :player :o
                      :board '(:o :x :x
                               :x :o nil
                               :x :o nil)}
            expected-board   '(:o :x :x
                               :x :o :o
                               :x :o nil)]
        (should= expected-board (take-square 6 context))
      )
    )
  )

  (context "Empty squares"
    (it "Finds empty squares"
      (let [board '(:o :x :x
                    :x :o nil
                    :x :o nil)]
        (should= #{6 9} (get-empty-squares board))
      )
    )

    (it "Recognizes an empty board"
      (let [board '(nil nil nil
                    nil nil nil
                    nil nil nil)]
        (should= (set (range 1 10)) (get-empty-squares board))
      )
    )

    (it "Recognizes a full board"
      (let [board '(:x :o :x
                    :o :o :x
                    :x :x :o)]
        (should= #{} (get-empty-squares board))
      )
    )
  )
)

(run-specs)
