#include <iostream>
#include <thread>
#include <atomic>

void add(int delta, std::atomic<int>* my_ref){
  int val = (*my_ref).load();
  while(!(*my_ref).compare_exchange_weak(val, val+delta,
  				      std::memory_order_release,
  				      std::memory_order_relaxed)) {
    val = (*my_ref).load();
  }
  std::cout << "Out from add: " << (*my_ref).load() << "\n";
}

int main(int argc, char *argv[]) {
  int num_runs = 1;
  int num_tragic_errors = 0;
  
  if(argc == 2) {
    num_runs = atoi(argv[1]);
  }

  std::atomic<int> my_int(0);
  for(int i = 0; i < num_runs; i++) {
    my_int.store(0);

    std::thread add_one_thread(add, 1, &my_int);
    std::thread add_two_thread(add, 2, &my_int);

    add_one_thread.join();
    add_two_thread.join();

    if(my_int != 3){
      num_tragic_errors++;
    }
    
    std::cout << "Final Value: " << my_int << "\n";
  }
  
  std::cout << "Total times wrong final value: " << num_tragic_errors << "\n";

  if(num_runs != 0) {
    std::cout << "Error rate: " << static_cast<double>(num_tragic_errors)*100/num_runs << "%\n";
  }
  return 1;
}
