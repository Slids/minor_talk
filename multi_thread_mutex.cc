#include <iostream>
#include <thread>
#include <mutex>


std::mutex ex;

void add_one(int* my_pointer){
  ex.lock();
  *my_pointer += 1;
  ex.unlock();
  //std::cout << "Out from add_one: " << *my_pointer << "\n";
}

void add_two(int* my_pointer){
  ex.lock();
  *my_pointer += 2;
  ex.unlock();
  //std::cout << "Out from add_two: " << *my_pointer << "\n";
}

int main(int argc, char *argv[]) {
  int num_runs = 1;
  int num_tragic_errors = 0;
  
  if(argc == 2) {
    num_runs = atoi(argv[1]);
  }

  for(int i = 0; i < num_runs; i++) {
    int my_int = 0;

    std::thread add_one_thread(add_one, &my_int);
    std::thread add_two_thread(add_two, &my_int);

    add_one_thread.join();
    add_two_thread.join();

    if(my_int != 3){
      num_tragic_errors++;
    }
    
    //std::cout << "Final Value: " << my_int << "\n";
  }
  std::cout << "Total times wrong final value: " << num_tragic_errors << "\n";

  if(num_runs != 0) {
    std::cout << "Error rate: " << static_cast<double>(num_tragic_errors)*100/num_runs << "%\n";
  }
  return 1;
}
