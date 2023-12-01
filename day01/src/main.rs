use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {

    println!("Calibration value TEST: {}", calculate_calibration_value("input_test.txt").to_string());
    println!("Calibration value EX1: {}", calculate_calibration_value("input_01.txt").to_string());
}

fn calculate_calibration_value(input_filename: &str) -> i32 {

    let mut calibration_value = 0;

    if let Ok(lines) = read_lines(input_filename) {
        for line in lines {
            if let Ok(ip) = line {

                //Look for the first number ...
                for c in ip.chars() {
                    let s = c.to_string();
                    if let Ok(n) = s.parse::<i32>() {
                        calibration_value += n * 10; 
                        break;
                    }
                }

                //... and repeat backwards
                for c in ip.chars().rev() {
                    let s = c.to_string();
                    if let Ok(n) = s.parse::<i32>() {
                        calibration_value += n; 
                        break;
                    }
                }
            }
        }
    }
    return calibration_value;
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
