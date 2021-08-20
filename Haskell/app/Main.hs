module Main where

import Control.Monad (replicateM)
import Criterion.Main (bench, bgroup, defaultMain, nf, whnf)
import Lib ()
import System.Random (getStdRandom, randomRIO)

-- Make a list with custom number of random ints from 0 to 99
makeIntValues :: Int -> IO [Integer]
makeIntValues num = replicateM num $ randomRIO (0, 99 :: Integer)

modifyValues :: [Integer] -> [Integer]
modifyValues = map (* 2)

-- Sum of all ints in a list
sumValues :: [Integer] -> Integer
sumValues = sum

reverseList :: [Integer] -> [Integer]
reverseList = reverse

main :: IO ()
main = do
  vals1000 <- makeIntValues 1000
  vals5000 <- makeIntValues 5000
  vals100000 <- makeIntValues 100000
  vals1000000 <- makeIntValues 1000000
  defaultMain
    -- modifyValues method main
    [ bgroup
        "Large list modification."
        [ bench
            "1000 integers modification"
            (nf modifyValues vals1000),
          bench
            "5000 integers modification"
            (nf modifyValues vals5000),
          bench
            "100000 integers modification"
            (nf modifyValues vals100000),
          bench
            "1000000 integers modification"
            (nf modifyValues vals1000000)
        ]
    ]

-- Reverse method main
{- [ bgroup
    "Large list reverse order."
    [ bench
        "1000 integers reverse order"
        (nf reverseList vals1000),
      bench
        "5000 integers reverse order"
        (nf reverseList vals5000),
      bench
        "100000 integers reverse order"
        (nf reverseList vals100000),
      bench
        "1000000 integers reverse order"
        (nf reverseList vals1000000)
    ]
 ] -}

-- Sum method main
{-  [ bgroup
    "Large list sum."
     [ bench
        "1000 integers sum"
        (nf sumValues vals1000),
      bench
        "5000 integers sum"
        (nf sumValues vals5000),
      bench
        "100000 integers sum"
        (nf sumValues vals100000),
      bench
        "1000000 integers sum"
        (nf sumValues vals1000000)
     ]
  ] -}
