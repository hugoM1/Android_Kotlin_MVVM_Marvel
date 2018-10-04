package com.mecalco.hugo.marvelapplication.base





import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.koin.android.architecture.ext.getViewModelByClass
import kotlin.reflect.KClass

/**
 * Constructor for a BaseActivity.
 *
 * @param viewModelClass KClass
 * @param <ModelT> The type of the BaseViewModel
 *
 * Prerequisite:
 * BaseViewModel instance has to be declared in {@link com.snidigital.watch.di.uiModule.Class}
 * @VMActivity annotation is required for subclass
 *
 * Example:
 * <pre>
 *     @VMActivity(layoutId = R.layout.activity_example)
 *     class ExampleActivity : BaseActivity<ExampleViewModel>(ExampleViewModel::class)
 * </pre>
 *
 *
 */
@VMActivity(0)
abstract class BaseActivity<ModelT : BaseActivityViewModel>(viewModelClass: KClass<ModelT>): AppCompatActivity() {

    protected val viewModel: ModelT by lazy {
        getViewModelByClass(true, viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(javaClass.getAnnotation(VMActivity::class.java).layoutId)
        setupViews(findViewById(android.R.id.content))
        subscribeToViewModel(viewModel)
    }

    override fun onStart() {
        viewModel.addObserver(lifecycle)
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.removeObserver(lifecycle)
    }
    protected fun getLayoutId(): Int {
        javaClass.getAnnotation(VMActivity::class.java)?.let {
            return it.layoutId
        }
        throw IllegalArgumentException("Missing VMActivity Annotation")
    }
    /**
     * View setup goes here.
     */
    protected abstract fun setupViews(view: View)

    /**
     * ViewModel observes goes here.
     */
    protected abstract fun subscribeToViewModel(viewModel : ModelT)


}
