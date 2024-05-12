package com.shipping.prueba_tecnica_movil.ui.view


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shipping.prueba_tecnica_movil.R
import com.shipping.prueba_tecnica_movil.adapters.RecyclerAdapterDeliveryInfo
import com.shipping.prueba_tecnica_movil.databinding.HomeBinding
import com.shipping.prueba_tecnica_movil.ui.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.shipping.prueba_tecnica_movil.ui.viewmodel.RemisionViewModel

@AndroidEntryPoint
class Home : Fragment() {
    private var _binding: HomeBinding? = null
    private lateinit var remisionViewModel: RemisionViewModel

    private val binding get() = _binding!!
    private val countryViewModel: CountryViewModel by viewModels()
    private  lateinit var mAdapter : RecyclerAdapterDeliveryInfo
    lateinit var mRecyclerView : RecyclerView
    private var currentPage = 0
    private  var hasExecuted:Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    var isloadin:Boolean =false
    var  shouldUpdate: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("your_shared_prefs", Context.MODE_PRIVATE)
        hasExecuted = sharedPreferences.getBoolean("has_executed", false)
        remisionViewModel = ViewModelProvider(requireActivity()).get(RemisionViewModel::class.java)
        mAdapter = RecyclerAdapterDeliveryInfo( remisionViewModel )

    
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater, container, false)
        val moreButton: ImageView = binding.root.findViewById (R.id.icon_button)
        moreButton.setOnClickListener { view ->
            showMoreActionsMenu(view)

        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        if( !hasExecuted ){
            Log.d("onResume", "onScrolled: ")
            mAdapter.clear()
            countryViewModel.onCreate()
            initDataIntoLIst()
            sharedPreferences.edit().putBoolean("has_executed", true).apply()
            hasExecuted = true

        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if( !it ) {
                mAdapter.clear()
                currentPage = 0
                initDataIntoLIst()

            }
            binding.loadingHome.isVisible  = it
        })
        val navController = findNavController()

        mAdapter.setNavController( navController )
        setUpRecyclerView()
        initScrollRecycler()
        initSearch(binding.root.findViewById<SearchView>(R.id.searchView))



    }
    private fun initSearch( searchView: SearchView ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty() && query.isNotEmpty()){
                    mAdapter.clear()
                    currentPage = 0
                    countryViewModel.getCountryByPrefix( query )
                        .observe(viewLifecycleOwner) { countries ->
                            countries.map { mAdapter.add(it) }
                            isloadin = false
                        }

                }
                else{

                    mAdapter.clear()
                    currentPage = 0
                    initDataIntoLIst()


                }

                return  false

            }

            override fun onQueryTextChange(query: String? ): Boolean {
                if(!query.isNullOrEmpty()  ){

                    mAdapter.clear()
                    currentPage = 0
                    countryViewModel.getCountryByPrefix(query)
                        .observe(viewLifecycleOwner) { countries ->
                            countries.map { mAdapter.add(it) }
                            isloadin = false
                        }



                }
                else{

                    mAdapter.clear()
                    currentPage = 0
                    initDataIntoLIst()

                }

                return false
            }
        })

    }
    fun setUpRecyclerView(){
        mRecyclerView =  binding.root.findViewById(R.id.rvDeliveryInfo) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext() )
        mAdapter.RecyclerAdapter( mutableListOf() , requireContext() )

        mRecyclerView.adapter = mAdapter
    }

    private fun showMoreActionsMenu(button: View) {
        val popupMenu = PopupMenu(requireContext() , button)
        requireActivity().menuInflater.inflate(R.menu.options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_recarge_home -> {
                    mAdapter.clear()
                    countryViewModel.onCreate()
                    true
                }

                R.id.menu_sing_out_home-> {
                    mAdapter.clear()
                    findNavController().navigate(R.id.action_blankFragment2_to_blankFragment22)

                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    fun initScrollRecycler(){
        mRecyclerView.addOnScrollListener( object  : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = mAdapter.itemCount
                Log.d("userTAGRELOAD", "onScrolled: [$totalItemCount] [$lastVisibleItemPosition]")
                if (totalItemCount -1 == lastVisibleItemPosition  && !isloadin  ) {
                    if(mAdapter.getSizeList() > 0 )
                        funtionScroll()
                }

            }
        })
    }

    fun funtionScroll(){
        isloadin = true;
        countryViewModel.getCountryByParts(currentPage + 5, currentPage)
            .observe(viewLifecycleOwner) { remisions ->
                remisions.map { mAdapter.add(it) }
                isloadin = false
            }
        currentPage += 5
    }

    fun initDataIntoLIst(){
        Log.d("INITFragment", "onScrolled: ")
        val dd = countryViewModel.getCountryByParts( 5, currentPage)
        dd.observe(viewLifecycleOwner) { remisions ->
            Log.d("GETDATAVIEW", "onScrolled: " + remisions)
            remisions.map { mAdapter.add(it) }
            if( mAdapter.getSizeList() == 0 ){
                mAdapter.clear()
                currentPage = 0
                countryViewModel.onCreate()

            }
        }
        currentPage = 5
    }

}