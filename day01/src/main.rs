use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::collections::HashMap;

fn main() {
    // 142
    println!("Calibration value TEST 01: {}", calculate_calibration_value("input_test_01.txt").to_string());
    // 5xxx4
    println!("Calibration value PUZZLE 01: {}", calculate_calibration_value("input_01.txt").to_string());
    // 281
    println!("Calibration value TEST 02: {}", calculate_calibration_value_extended("input_test_02.txt").to_string());
    // 5xxx1
    println!("Calibration value PUZZLE 02: {}", calculate_calibration_value_extended("input_02.txt").to_string());
}

fn calculate_calibration_value_extended(input_filename: &str) -> i32 {
    let mut calibration_value = 0;
    let translations = get_translations_dictionary();

    if let Ok(lines) = read_lines(input_filename) {
        for line in lines {
            if let Ok(mut ip) = line {

                for (word, value) in &translations {
                    ip = ip.replace(word, value);
                }

                calibration_value += get_calibration_value(ip);
            }
        }
    }

    calibration_value
}

fn get_translations_dictionary() -> HashMap<String, String> {
    let mut translations = HashMap::new();
    translations.insert(String::from("one"), String::from("on1e"));
    translations.insert(String::from("two"), String::from("tw2o"));
    translations.insert(String::from("three"), String::from("thr3ee"));
    translations.insert(String::from("four"), String::from("fo4ur"));
    translations.insert(String::from("five"), String::from("fi5ve"));
    translations.insert(String::from("six"), String::from("si6x"));
    translations.insert(String::from("seven"), String::from("sev7en"));
    translations.insert(String::from("eight"), String::from("eig8ht"));
    translations.insert(String::from("nine"), String::from("ni9ne"));
    translations
}

fn calculate_calibration_value(input_filename: &str) -> i32 {

    let mut calibration_value = 0;

    if let Ok(lines) = read_lines(input_filename) {
        for line in lines {
            if let Ok(ip) = line {
                calibration_value += get_calibration_value(ip);
            }
        }
    }
    calibration_value
}

fn get_calibration_value(s: String) -> i32 {
    let mut acc = 0;

    //Look for the first number ...
    for s in s.chars().map(|c| c.to_string()) {
        if let Ok(n) = s.parse::<i32>() {
            acc += n * 10; 
            break;
        }
    }

    //... and repeat backwards
    for s in s.chars().rev().map(|c| c.to_string()) {
        if let Ok(n) = s.parse::<i32>() {
            acc += n; 
            break;
        }
    }

    acc
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
