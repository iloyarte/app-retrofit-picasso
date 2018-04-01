package loyarte.ivan.iguanafixmobilechallenge.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.fragment.ContactDetailsFragment
import loyarte.ivan.iguanafixmobilechallenge.fragment.ContactsFragment

class MainActivity : AppCompatActivity() {

    private var currentFragment : Fragment? = null
    private val contactsFragment : ContactsFragment = ContactsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fContainer, contactsFragment, ContactsFragment.TAG)
                .commit()
    }

    fun addFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.add(R.id.fContainer, f)
        currentFragment = f
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }


    override fun onBackPressed() {
        if (currentFragment is ContactsFragment){
            (currentFragment as ContactsFragment).back()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
